using Food.Application.Account;
using MediatR;
using System;
using System.Collections.Generic;
using System.Text;

namespace Food.Application.User.Profile
{
    public class UserListCommand : IRequest<List<UserViewModel>>
    {
        public string UserName { get; set; }
    }
}
