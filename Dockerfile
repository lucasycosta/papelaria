#imagem java 
	FROM openjdk:21

#diretorio padrao
	WORKDIR /app

#arquivo JAR que sera copiado
	COPY target/papelaria-1.0.0.jar .
	
#configurações do banco de dados
	ENV DATABASE_URL=jdbc:postgresql://localhost:5432/papelaria
	ENV DB_USER=postgres
	ENV DB_PASSWORD=postgres

#comando a ser executado quando a imagem for executada 
	CMD ["java", "-jar", "papelaria-1.0.0.jar"]