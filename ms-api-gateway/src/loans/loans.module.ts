import { Module } from '@nestjs/common';
import { LoansService } from './loans.service';
import { LoansController } from './loans.controller';
import { ConfigModule } from '@nestjs/config';
import { HttpModule } from '@nestjs/axios';

@Module({
  controllers: [LoansController],
  providers: [LoansService],
  imports: [ConfigModule, HttpModule],
})
export class LoansModule {}
