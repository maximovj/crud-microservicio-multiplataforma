import { applyDecorators, HttpCode, HttpStatus } from '@nestjs/common';
import {
  ApiInternalServerErrorResponse,
  ApiNotFoundResponse,
  ApiOkResponse,
  ApiOperation,
  ApiParam,
} from '@nestjs/swagger';
import { User } from 'src/users/entities/user.entity';

export const SwaggerFindOne = () =>
  applyDecorators(
    HttpCode(HttpStatus.OK),
    ApiOperation({
      summary: 'Obtener un usuario por ID',
      description:
        'Obtiene los detalles de un usuario específico mediante su ID único',
    }),
    ApiParam({
      name: 'id',
      description: 'ID único del usuario a obtener',
      example: 1,
      type: Number,
    }),
    ApiOkResponse({
      description: 'Usuario encontrado exitosamente',
      type: User,
      schema: {
        example: {
          id: 1,
          firstname: 'Juan',
          lastname: 'Pérez',
          age: 25,
          email: 'juan.perezexample.com',
          isActive: true,
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
    ApiInternalServerErrorResponse({
      description: 'Error interno del servidor al obtener el usuario',
    }),
  );
