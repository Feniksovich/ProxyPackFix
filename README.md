# ProxyPackFix
Tiny BungeeCord plugin that prevents resending of resource pack installation on servers switch and make your players happy. Powered on [Protocolize](https://github.com/Exceptionflug/protocolize).

| MC Version | Tested | Supported | Notes                                                           |
| ---------- | ------ | --------- | --------------------------------------------------------------- |
| 1.8-1.15   | ❌    | N/A       | Packets mappings included, [but protocolize legacy module needed](https://github.com/Exceptionflug/protocolize#compatibility) |
| 1.16-1.19  | ✅    | ✅       |  |

## TODO
- [x] Wide proxy support
  - [x] BungeeCord
  - [x] Waterfall
  - [x] ~Velocity~ (won't be implemented, see [this](https://github.com/PaperMC/Velocity/issues/791))

- [x] Extra features  
  - [x] Reload and cache clearing support
  - [x] ~Several packs per player support~ (won't be implemented since Minecraft client doesn't allow to install several server packs)
  - [ ] Providing plugin API
