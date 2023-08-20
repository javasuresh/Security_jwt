package com.security.app.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="post")
@NoArgsConstructor
@Getter
@Setter
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int postId;
	private String title;
	private String content;
	private String imageName;
	private Date addDate;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private Category category;
	@ManyToOne
	private User user;
	//fetch = FetchType.EAGER, mappedBy = "topic", cascade = CascadeType.ALL
	@OneToMany(fetch = FetchType.EAGER,mappedBy = "post",cascade = CascadeType.ALL)
	private Set<Comment> comments=new HashSet<>();
}
