import { applyDecorators, HttpCode, HttpStatus } from '@nestjs/common';
import {
  ApiInternalServerErrorResponse,
  ApiOkResponse,
  ApiOperation,
} from '@nestjs/swagger';
import { User } from 'src/users/entities/user.entity';

export const SwaggerFindAll = () =>
  applyDecorators(
    HttpCode(HttpStatus.OK),
    ApiOperation({
      summary: 'Listar todos los usuarios',
      description:
        'Obtiene una lista de todos los usuarios registrados en el sistema (excluye la contraseña por seguridad)',
    }),
    ApiOkResponse({
      description: 'Lista de usuarios obtenida exitosamente',
      type: [User],
      schema: {
        example: [
          {
            id: 1,
            firstname: 'Juan',
            lastname: 'Pérez',
            age: 25,
            email: 'juan.perezexample.com',
            isActive: true,
          },
          {
            id: 2,
            firstname: 'María',
            lastname: 'García',
            age: 30,
            email: 'maria.garciaexample.com',
            isActive: true,
          },
        ],
      },
    }),
    ApiInternalServerErrorResponse({
      description: 'Error interno del servidor al obtener la lista de usuarios',
    }),
  );
