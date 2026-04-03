# Use Maven 3.9 with OpenJDK 17 as base image
FROM maven:3.9.4-openjdk-17-slim

# Set working directory
WORKDIR /app

# Install required packages for Selenium WebDriver
RUN apt-get update && apt-get install -y \
    wget \
    gnupg \
    unzip \
    curl \
    xvfb \
    && rm -rf /var/lib/apt/lists/*

# Install Chrome browser
RUN wget -q -O - https://dl.google.com/linux/linux_signing_key.pub | apt-key add - \
    && echo "deb [arch=amd64] http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list \
    && apt-get update \
    && apt-get install -y google-chrome-stable \
    && rm -rf /var/lib/apt/lists/*

# Copy pom.xml first for better Docker layer caching
COPY pom.xml .

# Download Maven dependencies
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src
COPY testing.xml .

# Build the project
RUN mvn clean compile test-compile

# Create directories for Allure reports
RUN mkdir -p target/allure-results

# Set environment variables for headless Chrome
ENV CHROME_BIN=/usr/bin/google-chrome
ENV CHROME_DRIVER=/usr/local/bin/chromedriver

# Expose port for Allure reports (optional)
EXPOSE 8080

# Command to run tests
CMD ["mvn", "test"]
