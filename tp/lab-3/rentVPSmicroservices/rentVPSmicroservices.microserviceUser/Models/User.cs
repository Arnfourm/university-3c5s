namespace rentVPSmicroservices.microserviceUser.Models
{
    public class User
    {
        public Guid Id { get; set; }
        public required string username { get; set; }
        public required string name { get; set; }
        public required string surname { get; set; }
        public required string email { get; set; }
        public required string password { get; set; }

        //private Guid _id;
        //private string _username;
        //private string _name;
        //private string _surname;
        //private string _email;
        //private string _passwd;

        //public User(Guid id, string username, string name, string surname, string email, string passwd) {
        //    _id = id;
        //    _username = username;
        //    _name = name;
        //    _surname = surname;
        //    _email = email;
        //    _passwd = passwd;
        //}

        //public Guid GetId() { return _id; }
        //public string GetUsername() { return _username; }
        //public string GetName() { return _name; }
        //public string GetSurname() { return _surname; }
        //public string GetEmail() { return _email; }
        //public string GetPassword() { return _passwd; }
    } 
}
