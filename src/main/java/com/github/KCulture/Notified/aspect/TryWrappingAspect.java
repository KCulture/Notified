package com.github.KCulture.Notified.aspect;

import java.net.UnknownHostException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import com.github.KCulture.Notified.Service.EmployeeSelectionStrategy;


@Aspect
public class TryWrappingAspect {
	
//	@Before("execution(* *(..))")
//	public void trywrapper(){
//	}
	
	@Around("execution(MongoClient com.github.KCulture.Notified.Service.MongoDatabaseService.initDatabase())")
	public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
    //Default Object that we can use to return to the consumer
    Object returnObject = null;
    try {
        System.out.println("YourAspect's aroundAdvice's body is now executed Before yourMethodAround is called.");
        //We choose to continue the call to the method in question
        returnObject = joinPoint.proceed();
        //If no exception is thrown we should land here and we can modify the returnObject, if we want to.
    } catch (UnknownHostException throwable) {
        //Here we can catch and modify any exceptions that are called
        //We could potentially not throw the exception to the caller and instead return "null" or a default object.
    	System.out.println(throwable.getClass() + "It was thrown.. thought you should know");
     //   throw throwable;
    }
    finally {
        //If we want to be sure that some of our code is executed even if we get an exception
        System.out.println("YourAspect's aroundAdvice's body is now executed After yourMethodAround is called.");
    }
    return returnObject;
		}
	
	@Around("execution(*  com.github.KCulture.Notified.Service.MongoDatabaseService.listOfAppraised(..))")
	public Object anotherAroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
    //Default Object that we can use to return to the consumer
    Object returnObject = null;
    try {
        System.out.println("YourAspect's aroundAdvice's body is now executed Before yourMethodAround is called.");
        //We choose to continue the call to the method in question
        returnObject = joinPoint.proceed();
        //If no exception is thrown we should land here and we can modify the returnObject, if we want to.
    } catch (Throwable throwable) {
        //Here we can catch and modify any exceptions that are called
        //We could potentially not throw the exception to the caller and instead return "null" or a default object.
    	System.out.println(throwable.getClass() + " was thrown.. thought you should know");
    //    throw throwable;
    }
    finally {
        //If we want to be sure that some of our code is executed even if we get an exception
        System.out.println("YourAspect's aroundAdvice's body is now executed After yourMethodAround is called.");
    }
    return returnObject;
		}
	
	@Around("execution(*  com.github.KCulture.Notified.Service.MongoDatabaseService.writeAppraisableToStorage(..))")
	public Object aAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
    //Default Object that we can use to return to the consumer
    Object returnObject = null;
    try {
        System.out.println("YourAspect's aroundAdvice's body is now executed Before yourMethodAround is called.");
        //We choose to continue the call to the method in question
        returnObject = joinPoint.proceed();
        //If no exception is thrown we should land here and we can modify the returnObject, if we want to.
    } catch (Throwable throwable) {
        //Here we can catch and modify any exceptions that are called
        //We could potentially not throw the exception to the caller and instead return "null" or a default object.
    	System.out.println(throwable.getClass() + " was thrown.. thought you should know");
   //     throw throwable;
    }
    finally {
        //If we want to be sure that some of our code is executed even if we get an exception
        System.out.println("YourAspect's aroundAdvice's body is now executed After yourMethodAround is called.");
    }
    return returnObject;
		}
	
	@Around("execution(*  com.github.KCulture.Notified.Service.EmailMessageService.sendMessage(..))")
	public Object bAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
    //Default Object that we can use to return to the consumer
    Object returnObject = null;
    try {
        System.out.println("YourAspect's aroundAdvice's body is now executed Before yourMethodAround is called.");
        //We choose to continue the call to the method in question
        returnObject = joinPoint.proceed();
        //If no exception is thrown we should land here and we can modify the returnObject, if we want to.
    } catch (Throwable throwable) {
        //Here we can catch and modify any exceptions that are called
        //We could potentially not throw the exception to the caller and instead return "null" or a default object.
    	System.out.println(throwable.getClass() + " was thrown.. thought you should know");
   //     throw throwable;
    }
    finally {
        //If we want to be sure that some of our code is executed even if we get an exception
        System.out.println("YourAspect's aroundAdvice's body is now executed After yourMethodAround is called.");
    }
    return returnObject;
		}

}
