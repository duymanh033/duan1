package com.example.heaven_motor.database;

public class Data_SQL {

    public static final String INSERT_User_img = "insert into Users(img) values"+"('R.drawable.ic_baseline_person_24')";
    
    public static final String INSERT_User = "Insert into Users(id, name, date, address, phone, cccd, passwork) values" +
            "('Lam','Tùng Lâm',18,'Thái Bình','0372514986','158649251','Admin')," +
            "('Duc','Văn đức',18,'Hà Nội','0351582258','8165619476','Admin')," +
            "('Sinh','Đỗ sinh',18,'Sao Hỏa','0351582258','132412659','Admin')," +
            "('Thang','Quang thắng',18,'Hà Nội','0351582258','124519862','Admin')," +
            "('Duy','Mạnh duy',18,'Hà Nội','0351582258','1924125245','Admin')," +
            "('Admin','Nguyễn Văn A',18,'Hà Nội','0351582258','27124594','Admin')";





    public static final String INSERT_Categories = "Insert into Categories(id, name, imager, BKS,capacity ,status,price,brand,year,categorie_id) values" +
            "('W12','SH2022','0111941','x12-31',09544,100,100000,'HONDA',2022,1)";
}


//
//"(id text PRIMARY KEY,"+
//        "name text,"+
//        "imager blob,"+
//        "BKS text,"+
//        "capacity integer,"+
//        "status integer,"+
//        "price integer,"+
//        "brand text,"+
//        "year integer,"+
//        "categorie_id integer REFERENCES Categories(id))";