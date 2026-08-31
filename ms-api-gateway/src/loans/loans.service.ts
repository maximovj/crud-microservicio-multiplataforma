import { Injectable, Logger } from '@nestjs/common';
import { CreateLoanDto } from './dto/create-loan.dto';
import { UpdateLoanDto } from './dto/update-loan.dto';
import { catchError, firstValueFrom } from 'rxjs';
import { HttpService } from '@nestjs/axios';
import { MsUrlApis } from 'src/commons/models/ms-apis.model';
import { AxiosError } from 'axios';
import { ConfigService } from '@nestjs/config';

@Injectable()
export class LoansService {
  private readonly logger = new Logger(LoansService.name);

  msApiLoans!: string;

  constructor(
    private readonly configService: ConfigService,
    private readonly httpService: HttpService,
  ) {
    const msUrlApis = configService.get<MsUrlApis>('msUrlApis', { infer: true });
    this.msApiLoans = msUrlApis.msApiLoans;
  }

  async create(createLoanDto: CreateLoanDto) {
    const { data } = await firstValueFrom(
      this.httpService.post(this.msApiLoans, createLoanDto).pipe(
        catchError((error: AxiosError) => {
          this.logger.error(error.response?.data);
          throw Error(`Failed communication with ${this.msApiLoans}`);
        }),
      ),
    );
    return data;
  }

  async findAll(): Promise<any[]> {
    const { data } = await firstValueFrom(
      this.httpService.get(`${this.msApiLoans}`).pipe(
        catchError((error: AxiosError) => {
          this.logger.error(error.response?.data);
          throw Error(`Failed communication with ${this.msApiLoans}`);
        }),
      ),
    );
    return data;
  }

  async findOne(id: number) {
    const { data } = await firstValueFrom(
      this.httpService.get(`${this.msApiLoans}/${id}`).pipe(
        catchError((error: AxiosError) => {
          this.logger.error(error.response?.data);
          throw Error(`Failed communication with ${this.msApiLoans}`);
        }),
      ),
    );
    return data;
  }

  async update(id: number, updateLoanDto: UpdateLoanDto) {
    const { data } = await firstValueFrom(
      this.httpService.put(`${this.msApiLoans}/${id}`, updateLoanDto).pipe(
        catchError((error: AxiosError) => {
          this.logger.error(error.response?.data);
          throw Error(`Failed communication with ${this.msApiLoans}`);
        }),
      ),
    );
    return data;
  }

  async remove(id: number) {
    await firstValueFrom(
      this.httpService.delete(`${this.msApiLoans}/${id}`).pipe(
        catchError((error: AxiosError) => {
          this.logger.error(error.response?.data);
          throw Error(`Failed communication with ${this.msApiLoans}`);
        }),
      ),
    );
  }
}
