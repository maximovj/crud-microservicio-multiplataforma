import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { UsersModule } from './users/users.module';
import { TypeOrmModule } from '@nestjs/typeorm';
import { User } from './users/entities/user.entity';
import { ConfigModule } from '@nestjs/config';
import configuration from './config/configuration';

@Module({
  imports: [
    ConfigModule.forRoot({
      envFilePath: '.development.env',
      load: [configuration],
    }),
    TypeOrmModule.forRoot({
      ssl: process.env.STAGE === 'prod',
      type: 'mysql',
      host: process.env.DB_HOST || 'localhost',
      port: +(process.env.DB_PORT || 3306),
      database: process.env.DB_NAME || 'mysqldb',
      username: process.env.DB_USERNAME || 'mysqluser',
      password: process.env.DB_PASSWORD || 'mysqlpassword',
      entities: [User],
      autoLoadEntities: true,
      synchronize: true, // En producción es pone en false
    }),
    UsersModule,
  ],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}
