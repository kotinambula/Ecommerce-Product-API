package virtusa.modern.repository;

import java.util.List;

import virtusa.modern.entity.Product;
import virtusa.modern.entity.Tag;

public interface TagRepository {
	public void createTag(Tag tag);
	public Tag updateTag(Integer tagId,Tag tag);
	public Tag getTagById(Integer tagId);
	public List<Tag> getAllTags();
	public void deleteTag(Integer tagId);
}
