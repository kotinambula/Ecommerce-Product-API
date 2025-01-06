package virtusa.modern.repository.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import virtusa.modern.entity.Tag;
import virtusa.modern.repository.TagRepository;

@Repository
public class TagRepositoryImpl implements TagRepository {

	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public void createTag(Tag tag) {
		entityManager.persist(tag);
		
	}

	@Override
	public Tag updateTag(Integer tagId, Tag tag) {
		Tag tagEntity = entityManager.find(Tag.class, tagId);
		if(tagEntity != null) {
			tagEntity.setTagName(tag.getTagName());
			tagEntity.setProducts(tag.getProducts());
			return tagEntity;
		}
		return null;
	}

	@Override
	public Tag getTagById(Integer tagId) {
		return entityManager.find(Tag.class, tagId);
	}

	@Override
	public List<Tag> getAllTags() {
		TypedQuery<Tag> query = entityManager.createQuery("SELECT tag FROM Tag tag", Tag.class);
		return query.getResultList();
	}

	@Override
	public void deleteTag(Integer tagId) {

		Tag tagEntity=entityManager.find(Tag.class, tagId);
		if(tagEntity != null) {
			entityManager.remove(tagEntity);
		}
	}

}
