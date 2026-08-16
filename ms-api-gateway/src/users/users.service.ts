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
    private readonly configService: ConfigService,
    private readonly httpService: HttpService,
  ){
    const msUrlApis = configService.get<MsUrlApis>('msUrlApis', { infer: true });
    this.msApiUsers = msUrlApis.msApiUsers;
  }

  async create(createUserDto: CreateUserDto): Promise<CreateUserDto> {
    const {data} = await firstValueFrom(
      this.httpService.post(this.msApiUsers, createUserDto)
      .pipe(
        catchError((error: AxiosError) => {
          this.logger.error(error.response?.data);
          throw `Failed communication with ${this.msApiUsers}`;
        }), // -- catchError
      ) // -- pipe
    );
    return data;
  }

  async findAll(): Promise<string> {
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

  async findOne(id: number): Promise<string> {
    const {data} = await firstValueFrom(
      this.httpService.get(this.msApiUsers, {params: { id }})
      .pipe(
        catchError((error: AxiosError) => {
          this.logger.error(error.response?.data);
          throw `Failed communication with ${this.msApiUsers}`;
        }), // -- catchError
      )
    );
    return data;
  }

  update(id: number, updateUserDto: UpdateUserDto) {
    return `This action updates a #${id} user`;
  }

  async remove(id: number): Promise<number> {
    const { data } = await firstValueFrom(
      this.httpService.delete(`${this.msApiUsers}/${id}`)
      .pipe(
        catchError((error: AxiosError) => {
          this.logger.error(error.response?.data);
          throw `Failed communication with ${this.msApiUsers}`;
        }), // -- catchError
      ), // -- pipe
    );
    return data;
  }
}
