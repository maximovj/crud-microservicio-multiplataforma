import { ApiProperty } from '@nestjs/swagger';
import { Exclude } from 'class-transformer';
import { Column, Entity, PrimaryGeneratedColumn } from 'typeorm';

@Entity({ name: 'users' })
export class User {
  @ApiProperty({
    description: 'ID único del usuario',
    example: 1,
    readOnly: true,
  })
  @PrimaryGeneratedColumn()
  id!: number;

  @ApiProperty({
    description: 'Nombre del usuario',
    example: 'Juan',
  })
  @Column('varchar', {
    nullable: false,
    length: 60,
  })
  firstname!: string;

  @ApiProperty({
    description: 'Apellido del usuario',
    example: 'Pérez',
  })
  @Column('varchar', {
    nullable: false,
    length: 60,
  })
  lastname!: string;

  @ApiProperty({
    description: 'Edad del usuario',
    example: 25,
  })
  @Column('int', {
    nullable: false,
  })
  age!: number;

  @ApiProperty({
    description: 'Correo electrónico del usuario (único)',
    example: 'juan.perez@example.com',
  })
  @Column('varchar', {
    nullable: false,
    unique: true,
    length: 120,
  })
  email!: string;

  @ApiProperty({
    description: 'Contraseña del usuario (no visible en respuestas)',
    example: 'MiContraseña123',
    writeOnly: true,
  })
  // select: false, no será visible en la entidad ORM
  @Column('varchar', {
    nullable: false,
    select: false,
  })
  @Exclude()
  password!: string;

  @ApiProperty({
    description: 'Estado del usuario (activo/inactivo)',
    example: true,
    default: true,
  })
  @Column('bool', {
    nullable: false,
    default: true,
  })
  isActive!: boolean;
}
