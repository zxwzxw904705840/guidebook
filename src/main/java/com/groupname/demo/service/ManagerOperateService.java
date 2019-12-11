package com.groupname.demo.service;

import com.groupname.demo.consts.Consts;
import com.groupname.demo.entity.*;
import com.groupname.demo.repository.*;
import com.groupname.demo.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerOperateService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    MajorRepository majorRepository;
    @Autowired
    ClassRepository classRepository;
    /*
    添加图书
     */
    public Result addBook(BookEntity book, UserEntity user){
        Result result = checkUserPermission(user);
        if(!result.isSuccess()){
            return result;
        }
        result = checkBook(book);
        if(!result.isSuccess()){
            return result;
        }
        bookRepository.save(book);
        return new Result(true, Consts.ADD_BOOK_SUCCESS);
    }
    /*
    添加课程
     */
    public Result addCourse(CourseEntity course,UserEntity user){
        Result result = checkUserPermission(user);
        if(!result.isSuccess()){
            return result;
        }
        result = checkCourse(course);
        if(!result.isSuccess()){
            return result;
        }
        courseRepository.save(course);
        return new Result(true,Consts.ADD_COURSE_SUCCESS);
    }
    /*
    开课
     */
    public Result addClass(ClassEntity classEntity,UserEntity user){
        Result result = checkUserPermission(user);
        if(!result.isSuccess()){
            return result;
        }
        result = checkClass(classEntity);
        if(!result.isSuccess()){
            return result;
        }
        /*
        if(result.getMessage().equals(Consts.GUIDEBOOK_EXISTS)){
            classEntity.setGuidebookStatus(Consts.Status.REVIEWING.getValue());
        }else{
            classEntity.setGuidebookStatus(Consts.Status.DEFAULT.getValue());
        }
        */
        classEntity.setGuidebook(null);
        classEntity.setGuidebookStatus(Consts.Status.DEFAULT.getValue());
        classEntity.resetClassNo();
        classRepository.save(classEntity);
        return new Result(true,Consts.ADD_CLASS_SUCCESS);
    }

     private Result checkUserPermission(UserEntity user){
        if(user==null) {
            return new Result(false, Consts.PERMISSION_DENIED);
        }
        UserEntity userEntity = userRepository.findByUserNo(user.getUserNo());
        if(userEntity==null||userEntity.getCharacters()!=Consts.UserType.MANAGER.getValue()){
            return new Result(false,Consts.PERMISSION_DENIED);
        }
        return new Result(true, Consts.ACCOUNT_CAN_USE);
     }
     private Result checkBook(BookEntity book){
         if(book==null){
             return new Result(false,Consts.ADD_BOOK_FAILED);
         }
         if(book.getIsbn()==null||book.getIsbn().equals("")){
             return new Result(false,Consts.ISBN_IS_EMPTY);
         }
         if(book.getIsbn().length()!=13){
             return new Result(false,Consts.ISBN_ERROR);
         }
         if(book.getBookName()==null||book.getBookName().equals("")){
             return new Result(false,Consts.BOOK_NAME_IS_EMPTY);
         }
         if(book.getPublishingHouse()==null||book.getPublishingHouse().equals("")){
             return new Result(false,Consts.PUBLISHING_HOUSE_IS_EMPTY);
         }
         if(book.getPublishingData()==null){
             return new Result(false,Consts.PUBLISHING_TIME_IS_EMPTY);
         }
         if(book.getAuthor()==null||book.getAuthor().equals("")){
             return new Result(false,Consts.AUTHOR_IS_EMPTY);
         }
         if(book.getAuthorMajor()==null){
             return new Result(false,Consts.AUTHOR_MAJOR_IS_EMPTY);
         }
         if(book.getAuthorMajor()<0||book.getAuthorMajor()>=Consts.BookMajor.values().length){
             return new Result(false,Consts.AUTHOR_MAJOR_ERROR);
         }
         if(bookRepository.findByIsbn(book.getIsbn())!=null){
             return new Result(false,Consts.BOOK_EXISTS);
         }
         return new Result(true,"");
     }

     private Result checkCourse(CourseEntity course){
        if(course==null){
            return new Result(false,Consts.ADD_COURSE_FAILED);
        }
        if(course.getCourseNo()==null||course.getCourseNo().equals("")){
            return new Result(false,Consts.COURSE_NO_IS_EMPTY);
        }
        if(course.getCourseName()==null||course.getCourseName().equals("")){
            return new Result(false,Consts.COURSE_NAME_IS_EMPTY);
        }
        if(course.getTerm()==null){
            return new Result(false,Consts.TERM_IS_EMPTY);
        }
        if(course.getTerm()<1||course.getTerm()>8){
            return new Result(false,Consts.TERM_ERROR);
        }
        if(course.getGuidebook()==null){
            return new Result(false,Consts.ADD_COURSE_FAILED);
        }
        if(course.getGuidebook().getIsbn()==null||course.getGuidebook().getIsbn().equals("")){
            return new Result(false,Consts.GUIDEBOOK_ISBN_IS_EMPTY);
        }
        if(course.getGuidebook().getIsbn().length()!=13){
            return new Result(false,Consts.GUIDEBOOK_ISBN_ERROR);
        }
        if(course.getCourseType()==null||course.getCourseType().equals("")){
            return new Result(false,Consts.COURSE_TYPE_IS_EMPTY);
        }
        if(course.getCourseType()<0||course.getCourseType()>=Consts.CourseType.values().length){
            return new Result(false,Consts.COURSE_TYPE_ERROR);
        }
        if(course.getCourseType()==Consts.CourseType.PUBLIC_OBLIGATORY_COURSE.getValue()&&course.getMajor()!=null&&course.getMajor().getMajorNo()!=null){
            return new Result(false,Consts.COURSE_MAJOR_NO_ERROR);
        }
        if(courseRepository.findByCourseNo(course.getCourseNo())!=null){
            return new Result(false,Consts.COURSE_EXISTS);
        }
        if(bookRepository.findByIsbn(course.getGuidebook().getIsbn())==null){
            return new Result(false,Consts.GUIDEBOOK_NOT_EXISTS_ERROR);
        }
        if(course.getMajor()!=null&&course.getMajor().getMajorNo()!=null&&majorRepository.findByMajorNo(course.getMajor().getMajorNo())==null){
            return new Result(false,Consts.COURSE_MAJOR_NO_ERROR);
        }
        return new Result(true,"");
     }

    private Result checkClass(ClassEntity classEntity){
        if(classEntity==null){
            return new Result(false,Consts.ADD_CLASS_FAILED);
        }
        if(classEntity.getTeacher()==null||classEntity.getTeacher().getUserNo()==null){
            return new Result(false,Consts.USERNO_NOT_EXISTS);
        }
        if(classEntity.getCourse()==null||courseRepository.findByCourseNo(classEntity.getCourse().getCourseNo())==null){
            return new Result(false,Consts.COURSE_NOT_EXISTS);
        }
        UserEntity teacher = userRepository.findByUserNo(classEntity.getTeacher().getUserNo());
        if(teacher==null){
            return new Result(false,Consts.USERNO_NOT_EXISTS);
        }
        if(teacher.getCharacters()!=Consts.UserType.TEACHER.getValue()){
            return new Result(false,Consts.ADD_CLASS_FAILED);
        }
        /*if(classEntity.getGuidebook()!=null){
            if(classEntity.getGuidebook().getIsbn().length()!=13){
                return new Result(false,Consts.GUIDEBOOK_ISBN_ERROR);
            }
            if(bookRepository.findByIsbn(classEntity.getGuidebook().getIsbn())==null){
                return new Result(false,Consts.GUIDEBOOK_NOT_EXISTS_ERROR);
            }
            return new Result(true,Consts.GUIDEBOOK_EXISTS);
        }*/
        return new Result(true,"");
    }
}
