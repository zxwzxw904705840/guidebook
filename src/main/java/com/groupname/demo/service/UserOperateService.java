package com.groupname.demo.service;

import com.groupname.demo.consts.Consts;
import com.groupname.demo.entity.*;
import com.groupname.demo.repository.*;
import com.groupname.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserOperateService {
    @Autowired
    ClassRepository classRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    ReviewRepository reviewRepository;
    /*
    查询全部课程，按名字查询课程，按首字母查询课程，按开课学年查询课程，按教师编号查询课程
    按课程编号查询开课列表
     */
    public Result<ArrayList<CourseEntity>> getAllClass(){
        ArrayList<CourseEntity> courseEntityArrayList = courseRepository.findAll();
        return new Result<>(true, Consts.INQUIRE_SUCCESS,courseEntityArrayList);
    }
    public Result<ArrayList<CourseEntity>> getAllCourseByCourseNameLike(String courseName){
        if(courseName==null){
            return new Result<>(false,Consts.INQUIRE_FAILED);
        }
        courseName="%"+courseName+"%";
        ArrayList<CourseEntity>courseEntityArrayList = courseRepository.findAllByCourseNameLike(courseName);
        return new Result<>(true,Consts.INQUIRE_SUCCESS,courseEntityArrayList);
    }
    public Result<ArrayList<CourseEntity>> getAllCourseByCourseEnglishNameLike(String courseEnglishName){
        if(courseEnglishName==null){
            return new Result<>(false,Consts.INQUIRE_FAILED);
        }
        courseEnglishName="%"+courseEnglishName+"%";
        ArrayList<CourseEntity>courseEntityArrayList = courseRepository.findAllByCourseEnglishNameLike(courseEnglishName);
        return new Result<>(true,Consts.INQUIRE_SUCCESS,courseEntityArrayList);
    }
    public Result<ArrayList<CourseEntity>> getAllCourseByTerm(Integer term){
        if(term==null||term<=0||term>8){
            return new Result<>(false,Consts.INQUIRE_FAILED);
        }
        ArrayList<CourseEntity>courseEntityArrayList = courseRepository.findAllByTerm(term);
        return new Result<>(true,Consts.INQUIRE_SUCCESS,courseEntityArrayList);
    }
    public Result<ArrayList<ClassEntity>> getAllClassByTeacher(UserEntity userEntity){
        if(userEntity==null||userEntity.getUserNo()==null){
            return new Result<>(false,Consts.INQUIRE_FAILED);
        }
        UserEntity teacher = userRepository.findByUserNo(userEntity.getUserNo());
        if(teacher==null||teacher.getCharacters()!=Consts.UserType.TEACHER.getValue()||teacher.getUserStatus()!=Consts.Status.NORMAL.getValue()){
            return new Result<>(false,Consts.PERMISSION_DENIED);
        }
        ArrayList<ClassEntity> classEntityArrayList = classRepository.findAllByTeacher(teacher);
        return new Result<>(true,Consts.INQUIRE_SUCCESS,classEntityArrayList);
    }
    public Result<ArrayList<ClassEntity>> getAllClassByCourseNo(String courseNo){
        if(courseNo==null){
            return new Result<>(false,Consts.INQUIRE_FAILED);
        }
        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setCourseNo(courseNo);
        ArrayList<ClassEntity> classEntityArrayList = classRepository.findAllByCourse(courseEntity);
        return new Result<>(true,Consts.INQUIRE_SUCCESS,classEntityArrayList);
    }

    /*
    查询全部教参，按名字或者isbn查询教参，按英文名查询教参，使用isbn获取书本完整信息
     */
    public Result<ArrayList<BookEntity>> getAllBook(){
        ArrayList<BookEntity> bookEntityArrayList = bookRepository.findAll();
        return new Result<>(true, Consts.INQUIRE_SUCCESS,bookEntityArrayList);
    }
    public Result<ArrayList<BookEntity>> getAllBookByBookNameLikeOrIsbn(String bookNameOrIsbn){
        if(bookNameOrIsbn==null){
            return new Result<>(false,Consts.INQUIRE_FAILED);
        }
        bookNameOrIsbn="%"+bookNameOrIsbn+"%";
        ArrayList<BookEntity> bookEntityArrayList = bookRepository.findAllByBookNameLike(bookNameOrIsbn);
        if(bookEntityArrayList.size()>0){
            return new Result<>(true, Consts.INQUIRE_SUCCESS,bookEntityArrayList);
        }
        bookEntityArrayList=bookRepository.findAllByIsbnLike(bookNameOrIsbn);
        return new Result<>(true, Consts.INQUIRE_SUCCESS,bookEntityArrayList);

    }
    public Result<ArrayList<BookEntity>> getAllBookByEnglishNameInitials(String initials){
        if(initials==null){
            return new Result<>(false,Consts.INQUIRE_FAILED);
        }
        initials=initials+"%";
        ArrayList<BookEntity> bookEntityArrayList = bookRepository.findAllByBookNameLike(initials);
        return new Result<>(true, Consts.INQUIRE_SUCCESS,bookEntityArrayList);
    }
    public Result<BookEntity> getBookByIsbn(String isbn){
        if(isbn==null){
            return new Result<>(false,Consts.INQUIRE_FAILED);
        }
        BookEntity book = bookRepository.findByIsbn(isbn);
        if(book==null){
            return new Result<>(false,Consts.BOOK_NOT_EXISTS);
        }
        return new Result<>(true,Consts.INQUIRE_SUCCESS,book);
    }
    /*
    教师：修改相关课程教参
     */
    public Result updateClassGuidebook(ClassEntity classToChange,UserEntity user){
        if(user==null||user.getUserNo()==null){
            return new Result(false,Consts.PERMISSION_DENIED);
        }
        if(classToChange==null||classToChange.getClassNo()==null){
            return new Result(false,Consts.GUIDEBOOK_UPDATE_FAILED);
        }
        UserEntity teacher = userRepository.findByUserNo(user.getUserNo());
        if(teacher==null||teacher.getCharacters()!=Consts.UserType.TEACHER.getValue()||teacher.getUserStatus()!=Consts.Status.NORMAL.getValue()){
            return new Result(false,Consts.PERMISSION_DENIED);
        }
        ClassEntity classEntity = classRepository.findByClassNo(classToChange.getClassNo());
        if(classEntity==null){
            return new Result(false,Consts.GUIDEBOOK_UPDATE_FAILED);
        }
        if(classEntity.getTeacher()==null||!classEntity.getTeacher().getUserNo().equals(teacher.getUserNo())){
            return new Result(false,Consts.PERMISSION_DENIED);
        }
        classEntity.setGuidebook(classToChange.getGuidebook());
        classEntity.setGuidebookStatus(Consts.Status.REVIEWING.getValue());
        reviewRepository.save(new ReviewEntity(Consts.ReviewType.CLASS_NO.getValue(),classEntity.getClassNo()));
        classRepository.save(classEntity);
        return new Result(true,Consts.GUIDEBOOK_UPDATE_WAITING_FOR_REVIEW);

    }
}
