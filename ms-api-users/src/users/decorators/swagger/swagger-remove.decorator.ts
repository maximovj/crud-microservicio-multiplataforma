import { applyDecorators, HttpCode, HttpStatus } from '@nestjs/common';
import {
  ApiInternalServerErrorResponse,
  ApiNotFoundResponse,
  ApiOkResponse,
  ApiOperation,
  ApiParam,
} from '@nestjs/swagger';

export const SwaggerRemove = () =>
  applyDecorators(
    HttpCode(HttpStatus.OK),
    ApiOperation({
      summary: 'Eliminar un usuario',
      description: 'Elimina permanentemente un usuario del sistema',
    }),
    ApiParam({
      name: 'id',
      description: 'ID único del usuario a eliminar',
      example: 1,
      type: Number,
    }),
    ApiOkResponse({
      description: 'Usuario eliminado exitosamente',
      schema: {
        example: {
          message: 'Usuario con ID (1) eliminado correctamente',
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
      description: 'Error interno del servidor al eliminar el usuario',
    }),
  );
