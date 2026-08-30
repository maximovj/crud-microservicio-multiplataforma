import { ApiProperty } from '@nestjs/swagger';
import {
  IsBoolean,
  IsEmail,
  IsInt,
  IsPositive,
  IsString,
  MaxLength,
  Min,
  MinLength,
} from 'class-validator';

export class CreateUserDto {
  /*
  @IsInt()
  @IsPositive()
  @IsOptional()
  id!: number; // Generado por ORM
  */

  @ApiProperty({
    description: 'Nombre del usuario',
    example: 'Juan',
    minLength: 3,
    maxLength: 30,
  })
  @IsString()
  @MinLength(3)
  @MaxLength(30)
  firstname!: string;

  @ApiProperty({
    description: 'Apellido del usuario',
    example: 'Pérez',
    minLength: 3,
    maxLength: 30,
  })
  @IsString()
  @MinLength(3)
  @MaxLength(30)
  lastname!: string;

  @ApiProperty({
    description: 'Edad del usuario (debe ser mayor o igual a 18 años)',
    example: 25,
    minimum: 18,
  })
  @IsInt()
  @IsPositive()
  @Min(18)
  age!: number;

  @ApiProperty({
    description: 'Correo electrónico del usuario (debe ser único)',
    example: 'juan.perez@example.com',
    format: 'email',
  })
  @IsEmail()
  email!: string;

  @ApiProperty({
    description: 'Contraseña del usuario (mínimo 6 caracteres)',
    example: 'MiContraseña123',
    minLength: 6,
    maxLength: 30,
    format: 'password',
  })
  @IsString()
  @MinLength(6)
  @MaxLength(30)
  password!: string;

  @ApiProperty({
    description: 'Estado del usuario (activo/inactivo)',
    example: true,
    default: true,
  })
  @IsBoolean()
  isActive!: boolean;
}
