import { Column, Entity, PrimaryGeneratedColumn } from 'typeorm';

@Entity({ name: 'users' })
export class User {
  @PrimaryGeneratedColumn()
  id!: number;

  @Column()
  firstname!: string;

  @Column()
  lastname!: string;

  @Column()
  age!: number;

  @Column({
    unique: true,
  })
  email!: string;

  // select: false, no será visible en la entidad ORM
  @Column({ select: false })
  password!: string;

  @Column({ default: true })
  isActive!: boolean;
}
