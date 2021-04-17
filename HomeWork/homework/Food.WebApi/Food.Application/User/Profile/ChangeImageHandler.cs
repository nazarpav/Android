using Food.Application.Account;
using Food.EFData;
using MediatR;
using Microsoft.Extensions.Configuration;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;

namespace Food.Application.User.Profile
{
	public class ChangeImageHandler : IRequestHandler<ChangeImageCommand, UserViewModel>
	{
		private readonly DataContext _context;
		private readonly IConfiguration _configuration;

		public ChangeImageHandler(IConfiguration configuration, DataContext context)
		{
			_configuration = configuration;
			_context = context;
		}

		public object DataTime { get; private set; }

		public async Task<UserViewModel> Handle(ChangeImageCommand request, CancellationToken cancellationToken)
		{
			var user = _context.Users.SingleOrDefault(x => x.UserName == request.UserName);
			if (request.Image == null || user == null)
				throw new Exception("User not found or image is null");

			/*if (await _context.Users.Where(x => x.UserName == request.UserName).AnyAsync())
			{
				throw new RestException(HttpStatusCode.BadRequest, new { UserName = "UserName already exist" });
			}*/

			//var base64Data = Regex.Match(request.Image, @"data:image/(?<type>.+?),(?<data>.+)").Groups["data"].Value;
			var binData = Convert.FromBase64String(request.Image);

			using (var stream = new MemoryStream(binData))
			{
				var filepath = user.Image;
				if (filepath == null)
					filepath = $"{Path.GetRandomFileName()}.jpg";
				var image = Bitmap.FromStream(stream);
				string dir = Path.Combine(Directory.GetCurrentDirectory(), _configuration.GetValue<string>("Folders:Images"));
				image.Save(Path.Combine(dir, filepath));
				user.Image = filepath;
				_context.Users.Update(user);
				await _context.SaveChangesAsync();
			}

			return new UserViewModel
			{
				DisplayName = user.DisplayName,
				Image = user.Image,
				UserName = user.UserName
			};


		}
	}
}
