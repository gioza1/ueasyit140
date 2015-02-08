package ueasy.it140.activities;

public class DatabaseObject {

       int _id;
       double _lat;
       double _lng;
       String _name;
       String _info;

        // Empty constructor
        public DatabaseObject(){

        }
        // constructor
        public DatabaseObject(int id, double lat, double lng,String name ,String info){
            this._id = id;
            this._lat=lat;
            this._lng=lng;
            this._name=name;
            this._info=info;
        }
        public DatabaseObject(double lat, double lng,String name ,String info){
             this._lat=lat;
                this._lng=lng;
                this._name=name;
                this._info=info;
        }
        // constructor

        // getting ID
        public int getID(){
            return this._id;
        }

        // setting id
        public void setID(int id){
            this._id = id;
        }


        public double getLat(){
            return this._lat;
        }

        public void setLat(double lat){
            this._lat = lat;
        }

        public double getLong(){
            return this._lng;
        }

        public void setLong(Double lng){
            this._lng = lng;
        }

        public String getName(){
            return this._name;
        }

        // setting id
        public void setName(String name){
            this._name = name;
        }

        public String getInfo(){
            return this._info;
        }

        // setting id
        public void setInfo(String info){
            this._info= info;
        }   
}