using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace rentVPSmicroservices.microserviceUser.Models
{
    public class User
    {
        [DatabaseGenerated(DatabaseGeneratedOption.Identity)]
        [Key]
        public Guid Id { get; set; }
        public required string username { get; set; }
        public required string name { get; set; }
        public required string surname { get; set; }
        public required string email { get; set; }
        public required string password { get; set; }

        public User(string username, string name, string surname, string email, string password)
        {
            this.username = username;
            this.name = name;
            this.surname = surname;
            this.email = email;
            this.password = password;
        }


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
