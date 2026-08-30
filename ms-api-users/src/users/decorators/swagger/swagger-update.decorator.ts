import { applyDecorators, HttpCode, HttpStatus } from '@nestjs/common';
import {
  ApiBody,
  ApiConflictResponse,
  ApiInternalServerErrorResponse,
  ApiNotFoundResponse,
  ApiOkResponse,
  ApiOperation,
  ApiParam,
} from '@nestjs/swagger';
import { UpdateUserDto } from 'src/users/dto/update-user.dto';
import { User } from 'src/users/entities/user.entity';

export const SwaggerUpdate = () =>
  applyDecorators(
    HttpCode(HttpStatus.OK),
    ApiOperation({
      summary: 'Actualizar un usuario',
      description:
        'Actualiza parcial o totalmente la información de un usuario existente',
    }),
    ApiParam({
      name: 'id',
      description: 'ID único del usuario a actualizar',
      example: 1,
      type: Number,
    }),
    ApiBody({ type: UpdateUserDto }),
    ApiOkResponse({
      description: 'Usuario actualizado exitosamente',
      type: User,
      schema: {
        example: {
          id: 1,
          firstname: 'Juan Carlos',
          lastname: 'Pérez Gómez',
          age: 26,
          email: 'juan.perezexample.com',
          isActive: false,
          updatedAt: '2024-01-16T14:20:00Z',
        },
      },
    }),
    ApiNotFoundResponse({
      description: 'Usuario no encontrado con el ID proporcionado',
      schema: {
        example: {
          statusCode: 404,
          message: 'Usuario con ID (1) no encontrado',
          error: 'Not Found',
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
      description: 'Error interno del servidor al actualizar el usuario',
    }),
  );
