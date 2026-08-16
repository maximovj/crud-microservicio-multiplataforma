import { Injectable, Logger } from '@nestjs/common';
import { CreateUserDto } from './dto/create-user.dto';
import { UpdateUserDto } from './dto/update-user.dto';
import { ConfigService } from '@nestjs/config';
import { MsUrlApis } from 'src/commons/models/ms-apis.model';
import { HttpService } from '@nestjs/axios';
import { AxiosError, AxiosResponse } from 'axios';
import { catchError, firstValueFrom, Observable } from 'rxjs';

@Injectable()
export class UsersService {
  private readonly logger =  new Logger(UsersService.name);
  
  msApiUsers!: string;

  constructor(
    private configService: ConfigService,
    private readonly httpService: HttpService,
  ){
    const msUrlApis = configService.get<MsUrlApis>('msUrlApis', { infer: true });
    this.msApiUsers = msUrlApis.msApiUsers;
  }

  create(createUserDto: CreateUserDto) {
    return `This action adds a new user`;
  }

  async findAll(): Promise<String> {
    const {data} = await firstValueFrom(
      this.httpService.get(`${this.msApiUsers}`)
      .pipe(
        catchError((error: AxiosError) => {
          this.logger.error(error.response?.data);
          throw `Failed communication with ${this.msApiUsers}`;
        }), // -- catchError
      ), // -- pipe
    );
    return data;
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
