-- Active: 1739467802696@@127.0.0.1@3306@audiencias
CREATE TABLE DISTRITO_JUDICIAL (
    dis_id INT PRIMARY KEY AUTO_INCREMENT,
    dis_nombre VARCHAR(50)
);

CREATE TABLE USUARIO (
    usr_id INT PRIMARY KEY AUTO_INCREMENT,
    usr_nombre VARCHAR(50),
    usr_username VARCHAR(50),
    usr_mail VARCHAR(50),
    usr_estado BOOLEAN,
    usr_login VARCHAR(50),
    usr_password VARCHAR(50),
    usr_isadmin BOOLEAN,
    dis_id INT,
    FOREIGN KEY(dis_id) REFERENCES DISTRITO_JUDICIAL(dis_id)
);

CREATE TABLE SALA (
    sal_id INT PRIMARY KEY AUTO_INCREMENT,
    sal_nombre VARCHAR(50),
    sal_lugar VARCHAR(50),
    dis_id INT,
    FOREIGN KEY(dis_id) REFERENCES DISTRITO_JUDICIAL(dis_id)
)

CREATE TABLE AUTORIDAD (
    aut_id INT PRIMARY KEY AUTO_INCREMENT,
    aut_nombre VARCHAR(50),
    aut_mail VARCHAR(100),
    aut_tipo ENUM('juez', 'fiscal', 'defensor'),
    dis_id INT,
    FOREIGN KEY(dis_id) REFERENCES DISTRITO_JUDICIAL(dis_id),
    aut_estado BOOLEAN
)

CREATE TABLE AUDIENCIA (
    aud_id INT PRIMARY KEY AUTO_INCREMENT,
    aud_nombre VARCHAR(50),
    aud_usrins INT,
    FOREIGN KEY(aud_usrins) REFERENCES USUARIO(usr_id),
    aud_fecins DATETIME,
    aud_usrmod INT,
    FOREIGN KEY(aud_usrmod) REFERENCES USUARIO(usr_id),
    aud_fecmod DATETIME,
    aud_estado BOOLEAN,
    aud_tipo ENUM('Demorada', 'Programada', 'Realizada', 'Suspendida'),
    sal_id INT,
    FOREIGN KEY (sal_id) REFERENCES SALA(sal_id),
    aud_fecha DATE,
    aud_hora TIME,
    aud_duracion TIME,
    aud_cuij INT UNIQUE,
    aud_caratula VARCHAR(50)   
)

CREATE TABLE AUDIENCIA_EXT (
    eau_id INT PRIMARY KEY AUTO_INCREMENT,
    eau_nombre VARCHAR(50),
    eau_usrins VARCHAR(50),
    eau_fecins DATE,
    eau_usrmod INT,
    eau_fecmod DATE,
    eau_estado BOOLEAN,
    aud_id INT,
    FOREIGN KEY (aud_id) REFERENCES AUDIENCIA(aud_id),
    aut_id INT,
    FOREIGN KEY (aut_id) REFERENCES AUTORIDAD(aut_id)
)

