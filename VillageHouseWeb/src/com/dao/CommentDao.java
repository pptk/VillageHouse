package com.dao;

import java.io.Serializable;
import java.util.List;

import com.model.CommentModel;



public interface CommentDao {
   
    List<CommentModel> getComment(String srid);
	
    Serializable add_Comment(CommentModel cm);
	
	String comment_deal(String name,String content,String time); //∆¿¬€—È÷§
}
