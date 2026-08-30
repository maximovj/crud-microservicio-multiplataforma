import {
  Body,
  Controller,
  Delete,
  Get,
  Param,
  Patch,
  Post,
} from '@nestjs/common';
import { ApiTags } from '@nestjs/swagger';
import { SwaggerCreate } from './decorators/swagger/swagger-create.decorator';
import { SwaggerFindAll } from './decorators/swagger/swagger-find-all.decorator';
import { SwaggerFindOne } from './decorators/swagger/swagger-find-one.decorator';
import { SwaggerRemove } from './decorators/swagger/swagger-remove.decorator';
import { SwaggerUpdate } from './decorators/swagger/swagger-update.decorator';
import { CreateUserDto } from './dto/create-user.dto';
import { UpdateUserDto } from './dto/update-user.dto';
import { UsersService } from './users.service';

@ApiTags('Users')
@Controller('users')
export class UsersController {
  constructor(private readonly usersService: UsersService) {}

  @Post()
  @SwaggerCreate()
  create(@Body() createUserDto: CreateUserDto) {
    return this.usersService.create(createUserDto);
  }

  @Get()
  @SwaggerFindAll()
  findAll() {
    return this.usersService.findAll();
  }

  @Get(':id')
  @SwaggerFindOne()
  findOne(@Param('id') id: string) {
    return this.usersService.findOne(+id);
  }

  @Patch(':id')
  @SwaggerUpdate()
  update(@Param('id') id: string, @Body() updateUserDto: UpdateUserDto) {
    return this.usersService.update(+id, updateUserDto);
  }

  @Delete(':id')
  @SwaggerRemove()
  remove(@Param('id') id: string) {
    return this.usersService.remove(+id);
  }
}
