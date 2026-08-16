import { Injectable } from '@nestjs/common';
import { CreateUserDto } from './dto/create-user.dto';
import { UpdateUserDto } from './dto/update-user.dto';
import { ConfigService } from '@nestjs/config';
import { MsUrlApis } from 'src/commons/models/ms-apis.model';

@Injectable()
export class UsersService {
  
  msApiUsers!: string;

  constructor(
    private configService: ConfigService,
  ){
    const msUrlApis = configService.get<MsUrlApis>('msUrlApis', { infer: true });
    this.msApiUsers = msUrlApis.msApiUsers;
  }

  create(createUserDto: CreateUserDto) {
    return `This action adds a new user`;
  }

  findAll() {
    return `This action returns all users`;
  }

  findOne(id: number) {
    return `This action returns a #${id} user`;
  }

  update(id: number, updateUserDto: UpdateUserDto) {
    return `This action updates a #${id} user`;
  }

  remove(id: number) {
    return `This action removes a #${id} user`;
  }
}
