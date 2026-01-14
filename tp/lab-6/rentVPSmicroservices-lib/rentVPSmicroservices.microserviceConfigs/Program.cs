using Microsoft.EntityFrameworkCore;
using rentVPSmicroservices.microserviceConfig.Contexts;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddControllers();
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

builder.Services.AddDbContext<ConfigContext>(
    options =>
    {
        options.UseNpgsql("User ID=luver_admin;Password=987654321;Host=192.168.0.192;Port=5432;Database=vps_configs;");
    }
);

builder.Logging.AddLog4Net("log4net.config");

var app = builder.Build();

if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();

app.UseAuthorization();

app.MapControllers();

app.Run();
