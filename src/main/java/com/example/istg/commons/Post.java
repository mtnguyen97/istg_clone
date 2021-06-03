package com.example.istg.commons;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Post implements Serializable {
	private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long id;
	@ElementCollection
	@NotNull
	@Size(min = 1, max = 20, message = "invalid_images_number")
	private List<String> images = new ArrayList<>();
	@Length(max = 10240, message = "caption_too_long")
	private String caption;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date createdAt;
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date deletedAt;
	@ManyToOne(cascade = CascadeType.PERSIST)
	private User postedBy;
	private boolean publicPost;

	public static final String IMG_DIR = "files";

	@PrePersist
	public void saveImages() {
		List<String> files = new ArrayList<>();
		for (String img : getImages()) {
			String fileName = UUID.randomUUID().toString();
			try {
				Files.createFile(Paths.get(IMG_DIR, fileName));
				Files.write(Paths.get(IMG_DIR, fileName), Base64.getDecoder().decode(img));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			files.add(fileName);
		}
		setImages(files);
		;
	}

	@PreRemove
	public void deleteImages() throws IOException {
		for (String fileName : images) {
			Files.deleteIfExists(Paths.get(IMG_DIR, fileName));
		}
	}

}
