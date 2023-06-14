package br.com.alangaraujo.api.controller;

import static br.com.alangaraujo.api.mapper.DocumentDTOMapper.toDto;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.alangaraujo.api.dto.ErrorDTO;
import br.com.alangaraujo.domain.exception.ConnectionIssueException;
import br.com.alangaraujo.domain.exception.NotFoundException;
import br.com.alangaraujo.domain.service.DocumentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = "Documents")
@RestController
@RequestMapping("/documents")
public class DocumentController {
	
	@Autowired
	private DocumentService documentService;

	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "Get a list of a file types of the project with details like file type count, a total of lines and bytes", 
		produces = APPLICATION_JSON_VALUE)
    @ApiResponses({
        @ApiResponse(code = 200, message = "Returns the file type list with details successfully"),
        @ApiResponse(code = 404, response = ErrorDTO.class, message = "Public repository not found or does not exist"),
        @ApiResponse(code = 504, response = ErrorDTO.class, message = "Provider access error")
    })
	@GetMapping
	public ResponseEntity findAll(@RequestParam String provider, @RequestParam String repositoryUri) {
		try {
			return ResponseEntity.ok(toDto(documentService.findAll(provider, repositoryUri)));
		} catch (NotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDTO(e.getMessage()));
		} catch (ConnectionIssueException e) {
			return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body(new ErrorDTO(e.getMessage()));
		}
	}
	
}
