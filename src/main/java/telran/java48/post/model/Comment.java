package telran.java48.post.model;

import java.time.LocalDateTime;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@EqualsAndHashCode(of = { "user", "dateCreated" })
public class Comment {
	@Setter
	String user;
	@Setter
	String message;
	LocalDateTime dateCreated;
	Integer likes;

	public Comment() {
		dateCreated = LocalDateTime.now();
		likes = 0;
	}

	public Comment(String user, String message) {
		this();
		this.user = user;
		this.message = message;
	}
	
	public void addLike() {
		likes++;
	}

}
