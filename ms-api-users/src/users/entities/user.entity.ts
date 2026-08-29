import { Column, Entity, PrimaryGeneratedColumn } from 'typeorm';

@Entity({ name: 'users' })
export class User {
  @PrimaryGeneratedColumn()
  id!: number;

  @Column({
    nullable: false,
  })
  firstname!: string;

  @Column({
    nullable: false,
  })
  lastname!: string;

  @Column({
    nullable: false,
  })
  age!: number;

  @Column({
    nullable: false,
    unique: true,
  })
  email!: string;

  // select: false, no será visible en la entidad ORM
  @Column({
    nullable: false,
    select: false,
  })
  password!: string;

  @Column({
    nullable: false,
    default: true,
  })
  isActive!: boolean;
}
