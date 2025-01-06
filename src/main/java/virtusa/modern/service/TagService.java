package virtusa.modern.service;

import java.util.List;

import virtusa.modern.entity.Product;
import virtusa.modern.entity.Tag;

public interface TagService {
	
	public void createTag(Tag tag);
	public Tag updateTag(Integer tagId,Tag tag);
	public Tag getTagById(Integer tagId);
	public List<Tag> getAllTags();
	public void deleteTag(Integer tagId);
}
