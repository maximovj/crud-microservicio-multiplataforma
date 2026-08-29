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

  @IsString()
  @MinLength(3)
  @MaxLength(30)
  firstname!: string;

  @IsString()
  @MinLength(3)
  @MaxLength(30)
  lastname!: string;

  @IsInt()
  @IsPositive()
  @Min(18)
  age!: number;

  @IsEmail()
  email!: string;

  @IsString()
  @MinLength(6)
  @MaxLength(30)
  password!: string;

  @IsBoolean()
  isActive!: boolean;
}
