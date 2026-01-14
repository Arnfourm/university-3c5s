using System;
using Microsoft.EntityFrameworkCore.Migrations;

#nullable disable

namespace rentVPSmicroservices.microserviceConfigs.Migrations
{
    /// <inheritdoc />
    public partial class DefaultMigration : Migration
    {
        /// <inheritdoc />
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.CreateTable(
                name: "Configurations",
                columns: table => new
                {
                    Id = table.Column<Guid>(type: "uuid", nullable: false),
                    OS = table.Column<string>(type: "text", nullable: false),
                    CPU_name = table.Column<string>(type: "text", nullable: false),
                    CPU_ghz = table.Column<float>(type: "real", nullable: false),
                    GPU_name = table.Column<string>(type: "text", nullable: false),
                    GPU_vram = table.Column<int>(type: "integer", nullable: false),
                    RAM = table.Column<int>(type: "integer", nullable: false),
                    disk_size = table.Column<int>(type: "integer", nullable: false)
                },
                constraints: table =>
                {
                    table.PrimaryKey("PK_Configurations", x => x.Id);
                });
        }

        /// <inheritdoc />
        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropTable(
                name: "Configurations");
        }
    }
}
