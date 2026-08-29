import { Exclude } from 'class-transformer';
import { Column, Entity, PrimaryGeneratedColumn } from 'typeorm';

@Entity({ name: 'users' })
export class User {
  @PrimaryGeneratedColumn()
  id!: number;

  @Column('varchar', {
    nullable: false,
    length: 60,
  })
  firstname!: string;

  @Column('varchar', {
    nullable: false,
    length: 60,
  })
  lastname!: string;

  @Column('int', {
    nullable: false,
  })
  age!: number;

  @Column('varchar', {
    nullable: false,
    unique: true,
    length: 120,
  })
  email!: string;

  // select: false, no será visible en la entidad ORM
  @Column('varchar', {
    nullable: false,
    select: false,
  })
  @Exclude()
  password!: string;

  @Column('bool', {
    nullable: false,
    default: true,
  })
  isActive!: boolean;
}
