package demo.zhongshi.com.myapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

@Aspect
public class CheckLoginAspectJ {
    /**
     * 找到指定注解的切点
     */
    @Pointcut("execution(@demo.zhongshi.com.myapplication.CheckLogin * *(..))")
    public void executeCheckLogin(){}

    /**
     * 切面
     * @param point
     * @return
     * @throws Throwable
     */
    @Around("executeCheckLogin()")
    public Object checkLogin(ProceedingJoinPoint point) throws Throwable{
        MethodSignature signature = (MethodSignature) point.getSignature();
        CheckLogin checkLogin = signature.getMethod().getAnnotation(CheckLogin.class);
        if(null != checkLogin){
            boolean isLogin = Constants.isLogin();
            if(isLogin){
                Log.e("--->", "当前已登录");
                return point.proceed();
            }else {
                Log.e("--->", "请登陆账号");
                Context context = (Context) point.getThis();
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
                return null;
            }
        }
        Log.e("--->", "注解为空");
        return point.proceed();
    }
}
