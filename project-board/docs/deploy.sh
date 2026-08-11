###### 로컬에서 실행 ######
java -jar build/libs/spring-board-0.0.1-SNAPSHOT.jar # 로컬에서 작동 실험
#로컬에서 빌드된 최종 코드를 EC2에 업로드
scp -i "first-key.pem" build/libs/spring-board-0.0.1-SNAPSHOT.jar ec2-user@3.37.216.219:/home/ec2-user/

# 깃 관리 예외사항
**/*.pem

###### EC2 에서 실행 ######
#EC2 원격 접속
ssh -i "first-key.pem" ec2-user@3.37.216.219

sudo yum update -y

# 외부 저장소 설정
sudo tee /etc/yum.repos.d/adoptium.repo << 'EOF'
[Adoptium]
name=Adoptium
baseurl=https://packages.adoptium.net/artifactory/rpm/amazonlinux/2/x86_64
enabled=1
gpgcheck=1
gpgkey=https://packages.adoptium.net/artifactory/api/gpg/key/public
EOF
# 설정 확인
cat /etc/yum.repos.d/adoptium.repo

# 가상 서버 설치
sudo yum install temurin-25-jdk -y
java --version

# 환경 변수 등록 및 적용
# JAVA_HOME 환경 변수 등록
echo 'export JAVA_HOME=/usr/lib/jvm/java-25-temurin-jdk' | sudo tee -a /etc/profile
source /etc/profile

#최종 실행
java -jar spring-board-0.0.1-SNAPSHOT.jar

#mysql 클라이언트로 RDS 서비스 접속
mysql -h board-db.clqsc4agmwrh.ap-northeast-2.rds.amazonaws.com -u admin -p

CREATE DATABASE board_db;

-- 외부 접속 권한 사용자 생성
CREATE USER 'board_app'@'%' IDENTIFIED BY 'Board123!';
-- board_db 데이터베이스에 대한 전권 부여
GRANT ALL PRIVILEGES ON board_db.* TO 'board_app'@'%';
-- 권한 변경 사항 적용
FLUSH PRIVILEGES;

-- MySQL 클라이언트 종료
EXIT;
