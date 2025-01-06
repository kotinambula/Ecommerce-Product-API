package virtusa.modern.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import virtusa.modern.entity.Product;
import virtusa.modern.entity.Tag;
import virtusa.modern.repository.TagRepository;
import virtusa.modern.service.TagService;

@Service
public class TagServiceImpl implements TagService {

	private TagRepository tagRepository;
	
	public TagServiceImpl(TagRepository tagRepository) {
		super();
		this.tagRepository = tagRepository;
	}

	@Override
	public void createTag(Tag tag) {
 
		tagRepository.createTag(tag);
	}

	@Override
	public Tag updateTag(Integer tagId, Tag tag) {
		return tagRepository.updateTag(tagId, tag) ;
	}

	@Override
	public Tag getTagById(Integer tagId) {
		return tagRepository.getTagById(tagId);
	}

	@Override
	public List<Tag> getAllTags() {
		return tagRepository.getAllTags();
	}

	@Override
	public void deleteTag(Integer tagId) {

		tagRepository.deleteTag(tagId);
	}


}
