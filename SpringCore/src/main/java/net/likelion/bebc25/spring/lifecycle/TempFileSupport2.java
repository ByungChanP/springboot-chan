package net.likelion.bebc25.spring.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.aspectj.lang.reflect.InitializerSignature;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.security.auth.Destroyable;

// 임시로 파일을 만들어서 파일에 로그를 출력
@Component
public class TempFileSupport2 implements InitializingBean, DisposableBean {
    @Value("resources/temp.log")
    private String filePath; // 파일 경로
    public TempFileSupport2(){
        System.out.println("생성자 호출됨."  + filePath);
    }

    // 네트워크, 파일시스템 연결 같은 무거운 작업
    // 비지니스 메서드 호출 전에 실행해야 할 작업
    @Override // 초기화 콜백
    public void afterPropertiesSet() throws Exception {
        System.out.println(filePath + "경로의 FileOutputStream 생성.");
    }

    public void writelog(String msg) {
        System.out.println(filePath + "에 로그 저장: " + msg);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println(filePath + "경로의 FileOutputStream 닫기.");
    }
}
