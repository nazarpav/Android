using Food.Application.Account;
using Food.Application.User.Profile;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Food.WebApi.Controllers
{
    [Authorize]
    public class UserController : BaseController
    {
        [HttpGet("profile")]
        public async Task<ActionResult<UserViewModel>> ProfileAsync()
        {
            UserProfileCommand userCommand = new UserProfileCommand
            {
                UserName = User.Claims
                .FirstOrDefault(x => x.Type == "username").Value
            };

            return await Mediator.Send(userCommand);
        }
        [HttpPost("change-image")]
        public async Task<ActionResult<UserViewModel>> UsersAsync(ChangeImageCommand command)
        {
            command.UserName = User.Claims
                .FirstOrDefault(x => x.Type == "username").Value;

            return await Mediator.Send(command);
        }
        [AllowAnonymous]
        [HttpGet("users")]
        public async Task<ActionResult<List<UserViewModel>>> UsersAsync()
        {
            UserListCommand userCommand = new UserListCommand
            {
            };
            return await Mediator.Send(userCommand);
        }
    }
}
