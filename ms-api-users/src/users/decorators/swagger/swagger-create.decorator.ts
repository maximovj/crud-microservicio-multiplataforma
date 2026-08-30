import { applyDecorators, HttpCode, HttpStatus } from '@nestjs/common';
import {
  ApiOperation,
  ApiBody,
  ApiCreatedResponse,
  ApiConflictResponse,
  ApiInternalServerErrorResponse,
} from '@nestjs/swagger';
import { CreateUserDto } from 'src/users/dto/create-user.dto';
import { User } from 'src/users/entities/user.entity';

export const SwaggerCreate = () =>
  applyDecorators(
    HttpCode(HttpStatus.CREATED),
    ApiOperation({
      summary: 'Crear un nuevo usuario',
      description:
        'Crea un nuevo usuario en el sistema con los datos proporcionados. La contraseña será encriptada automáticamente.',
    }),
    ApiBody({ type: CreateUserDto }),
    ApiCreatedResponse({
      description: 'Usuario creado exitosamente',
      type: User,
      schema: {
        example: {
          id: 1,
          firstname: 'Juan',
          lastname: 'Pérez',
          age: 25,
          email: 'juan.pereexample.com',
          isActive: true,
          createdAt: '2024-01-15T10:30:00Z',
          updatedAt: '2024-01-15T10:30:00Z',
        },
      },
    }),
    ApiConflictResponse({
      description: 'El email ya está registrado en el sistema',
      schema: {
        example: {
          statusCode: 409,
          message: 'El email ya esta registrado',
          error: 'Conflict',
        },
      },
    }),
    ApiInternalServerErrorResponse({
      description: 'Error interno del servidor al crear el usuario',
    }),
  );
