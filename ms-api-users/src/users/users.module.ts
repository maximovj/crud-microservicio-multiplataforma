import { Module } from '@nestjs/common';
import { UsersService } from './users.service';
import { UsersController } from './users.controller';
import { TypeOrmModule } from '@nestjs/typeorm';
import { User } from './entities/user.entity';
import { EncryptionService } from '../common/services/encryption.service';

@Module({
  controllers: [UsersController],
  providers: [UsersService, EncryptionService],
  imports: [TypeOrmModule.forFeature([User])],
})
export class UsersModule {}
