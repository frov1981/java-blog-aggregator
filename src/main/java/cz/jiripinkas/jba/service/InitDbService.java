package cz.jiripinkas.jba.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cz.jiripinkas.jba.entity.Blog;
import cz.jiripinkas.jba.entity.Item;
import cz.jiripinkas.jba.entity.Role;
import cz.jiripinkas.jba.entity.User;
import cz.jiripinkas.jba.repository.BlogRepository;
import cz.jiripinkas.jba.repository.ItemRepository;
import cz.jiripinkas.jba.repository.RoleRepository;
import cz.jiripinkas.jba.repository.UserRepository;

@Transactional
@Service
public class InitDbService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BlogRepository blogRepository;

	@Autowired
	private ItemRepository itemRepository;

	@PostConstruct
	public void init() {

		if (roleRepository.findByName("ROLE_ADMIN") == null) {
			Role roleUser = new Role();
			roleUser.setName("ROLE_USER");
			roleRepository.save(roleUser);

			Role roleAdmin = new Role();
			roleAdmin.setName("ROLE_ADMIN");
			roleRepository.save(roleAdmin);

			User userAdmin = new User();
			userAdmin.setName("admin");
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			userAdmin.setPassword(encoder.encode("admin"));
			userAdmin.setEnabled(true);
			List<Role> roles = new ArrayList<Role>();
			roles.add(roleAdmin);
			roles.add(roleUser);
			userAdmin.setRoles(roles);
			userRepository.save(userAdmin);

			Blog blogJava = new Blog();
			blogJava.setName("JavaVids");
			blogJava.setUrl("http://feeds.feedburner.com/javavids?format=xml");
			blogJava.setUser(userAdmin);
			blogRepository.save(blogJava);

			Item item1 = new Item();
			item1.setBlog(blogJava);
			item1.setTitle("First");
			item1.setLink("http://www.javavids.com");
			item1.setPublishedDate(new Date());
			itemRepository.save(item1);

			Item item2 = new Item();
			item2.setBlog(blogJava);
			item2.setTitle("Second");
			item2.setLink("http://www.javavidsOkis.com");
			item2.setPublishedDate(new Date());
			itemRepository.save(item2);

			Item item3 = new Item();
			item3.setBlog(blogJava);
			item3.setTitle("Thrid");
			item3.setLink("http://www.oleole.com");
			item3.setPublishedDate(new Date());
			itemRepository.save(item3);

			Item item4 = new Item();
			item2.setBlog(blogJava);
			item2.setTitle("Four");
			item2.setLink("http://www.xvideos.com");
			item2.setPublishedDate(new Date());
			itemRepository.save(item4);

			Item item5 = new Item();
			item5.setBlog(blogJava);
			item5.setTitle("Five");
			item5.setLink("http://www.kids.com");
			item5.setPublishedDate(new Date());
			itemRepository.save(item5);

			Item item6 = new Item();
			item6.setBlog(blogJava);
			item6.setTitle("Six");
			item6.setLink("http://www.underboard.com");
			item6.setPublishedDate(new Date());
			itemRepository.save(item6);

			Item item7 = new Item();
			item7.setBlog(blogJava);
			item7.setTitle("Seven");
			item7.setLink("http://www.vids.com");
			item7.setPublishedDate(new Date());
			itemRepository.save(item7);
		}

	}

}
