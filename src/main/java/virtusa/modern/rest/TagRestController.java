package virtusa.modern.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import virtusa.modern.entity.Tag;
import virtusa.modern.repository.TagRepository;

@RestController
@RequestMapping("/tag")
public class TagRestController {

	private TagRepository tagRepository;

	public TagRestController(TagRepository tagRepository) {
		this.tagRepository = tagRepository;
	}

	@PostMapping("/save")
    @Operation(summary = "Create a new tag", description = "Add a new tag to the database")
	public void createTag(@RequestBody Tag tag) {
		tagRepository.createTag(tag);
	}
    
    @Operation(summary = "Update a tag", description = "Update a tag from the database using its ID")
	@PutMapping("/{tagId}")
	public ResponseEntity<Tag> updateTag(@PathVariable Integer tagId, @RequestBody Tag tag) {

		return new ResponseEntity<Tag>(tagRepository.updateTag(tagId, tag), HttpStatus.OK);
	}

	 @Operation(summary = "Get a tag by ID", description = "Retrieve a single tag by its ID")
	@GetMapping("/{tagId}")
	public ResponseEntity<Tag> getTagById(@PathVariable Integer tagId) {

		return new ResponseEntity<Tag>(tagRepository.getTagById(tagId), HttpStatus.OK);

	}

	@GetMapping("/")
    @Operation(summary = "Get all tags", description = "Retrieve a list of all tags available")
	public ResponseEntity<List<Tag>> getAllTags() {

		return new ResponseEntity<List<Tag>>(tagRepository.getAllTags(), HttpStatus.OK);

	}
	
    @Operation(summary = "Delete a tag", description = "Delete a tag from the database using its ID")
	@DeleteMapping("{tagId}")
	public void deleteTag(@PathVariable Integer tagId) {
		tagRepository.deleteTag(tagId);

	}
}
