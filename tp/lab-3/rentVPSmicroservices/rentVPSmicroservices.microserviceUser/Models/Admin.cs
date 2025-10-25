
using System.ComponentModel.DataAnnotations.Schema;

namespace rentVPSmicroservices.microserviceUser.Models
{
    public class Admin
    {
        public Guid Id {  get; set; }
        public int permission_lvl { get; set; }
        public required string secret_username { get; set; }
        public required string secret_password { get; set; }
        public Guid UserId { get; set; }

        [ForeignKey("UserId")]
        public required User User { get; set; }

        //private int _permission_lvl;
        //private string _admin_username;
        //private string _admin_passwd;

        //public Admin(Guid id,
        //             string username,
        //             string name,
        //             string surname,
        //             string email,
        //             string passwd,
        //             int permission_lvl,
        //             string admin_username,
        //             string admin_passwd) 
        //        : base(id, username, name, surname, email, passwd)
        //{
        //    _permission_lvl = permission_lvl;
        //    _admin_username = admin_username;
        //    _admin_passwd = admin_passwd;
        //}

        //public int GetPermissionLvl() { return _permission_lvl; }
        //public string GetAdminUsername() { return _admin_username; }
        //public string GetAdminPassword() { return _admin_passwd; }
    }
}
