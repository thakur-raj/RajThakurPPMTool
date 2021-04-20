package raj.thakur.ppmtool.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ProjectIdException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public ProjectIdException(String message) {
		super(message);
	}
}
