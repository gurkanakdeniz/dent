FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD

COPY pom.xml /dent-crawler-build/

COPY src /dent-crawler-build/src/

WORKDIR /dent-crawler-build/

RUN mvn package -Dmaven.test.skip=true 

FROM openjdk:8-jre-buster

RUN apt-get update && apt-get install -y \
	apt-transport-https \
	ca-certificates \
	curl \
	unzip \
	gnupg \
	xvfb \
	libasound2 \ 
	libatk1.0-0 \ 
	libc6 \ 
	libcairo2 \
	libcups2 \
	libdbus-1-3 \
	libexpat1 \
	libfontconfig1 \
	libgcc1 \
	libgdk-pixbuf2.0-0 \
	libglib2.0-0 \
	libgtk-3-0 \
	libgconf-2-4 \
	libnspr4 \
	libpango-1.0-0 \
	libpangocairo-1.0-0 \
	libstdc++6 \
	libx11-6 \
	libxcb1 \
	libxcomposite1 \
	libxcursor1 \
	libxdamage1 \
	libxext6 \
	libxfixes3 \
	libxi6 \
	libxrandr2 \
	libxrender1 \
	fonts-liberation \
	libnss3 \
	xdg-utils \
	--no-install-recommends \
	&& curl -sSL https://dl.google.com/linux/linux_signing_key.pub | apt-key add - \
	&& echo "deb https://dl.google.com/linux/chrome/deb/ stable main" > /etc/apt/sources.list.d/google-chrome.list \
	&& apt-get update && apt-get install -y \
	google-chrome-stable \
	fontconfig \
	fonts-ipafont-gothic \
	fonts-wqy-zenhei \
	fonts-thai-tlwg \
	fonts-kacst \
	fonts-symbola \
	fonts-noto \
	fonts-freefont-ttf \
	--no-install-recommends \
	&& apt-get purge --auto-remove -y \
	&& rm -rf /var/lib/apt/lists/* \ 
	&& wget -O /tmp/chromedriver.zip http://chromedriver.storage.googleapis.com/2.41/chromedriver_linux64.zip\
	&& unzip /tmp/chromedriver.zip chromedriver -d /usr/bin/ \
	&& chmod +x /usr/bin/chromedriver 
	
# && wget -O /tmp/chromedriver.zip http://chromedriver.storage.googleapis.com/`curl -sS chromedriver.storage.googleapis.com/LATEST_RELEASE`/chromedriver_linux64.zip\
	

RUN groupadd -r chrome && useradd -r -g chrome -G audio,video chrome \
	&& mkdir -p /home/chrome && chown -R chrome:chrome /home/chrome

USER chrome

WORKDIR /dent-crawler

EXPOSE 8082

COPY --from=MAVEN_BUILD /dent-crawler-build/target/*.jar dent-crawler.jar

ENTRYPOINT ["java","-jar","/dent-crawler/dent-crawler.jar"]


