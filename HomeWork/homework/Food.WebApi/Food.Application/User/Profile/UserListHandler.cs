using Food.Application.Account;
using Food.Application.Exceptions;
using Food.Domain;
using MediatR;
using Microsoft.AspNetCore.Identity;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace Food.Application.User.Profile
{
    public class UserListHandler : IRequestHandler<UserListCommand, List<UserViewModel>>
    {
        private readonly UserManager<AppUser> _userManager;
        public UserListHandler(UserManager<AppUser> userManager)
        {
            _userManager = userManager;
        }
        public async Task<List<UserViewModel>> Handle(UserListCommand request, CancellationToken cancellationToken)
        {
            var users = _userManager.Users.Select(x =>
            new UserViewModel
            {
                DisplayName = x.DisplayName,
                UserName = x.UserName,
                Image = "2.jpg"
            }).ToList();
            if (users == null)
            {
                throw new RestException(HttpStatusCode.NotFound);
            }

            return users;
            //throw new RestException(HttpStatusCode.NotFound);
        }
    }
}
