#!/bin/bash

yum update -y

# JDK 라이브러리 정보 등록
tee /etc/yum.repos.d/adoptium.repo << 'EOF'
[Adoptium]
name=Adoptium
baseurl=https://packages.adoptium.net/artifactory/rpm/amazonlinux/2/x86_64
enabled=1
gpgcheck=1
gpgkey=https://packages.adoptium.net/artifactory/api/gpg/key/public
EOF

# JDK 설치
yum install temurin-25-jdk -y

# 환경 변수 등록 및 적용
# JAVA_HOME 환경 변수 등록
echo 'export JAVA_HOME=/usr/lib/jvm/java-25-temurin-jdk' | tee -a /etc/profile.d/java.sh
export JAVA_HOME=/usr/lib/jvm/java-25-temurin-jdk
