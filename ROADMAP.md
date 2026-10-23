# RecordCraft — Development Roadmap

> Planned features only. This document does not imply that any of the items below have been implemented or released.

## v1.1.0 — Multiplayer Music Update (planned)

### Shared audio
- Stream music from a playing Discman or Vinyl Player to other players without requiring them to have the original files locally.
- Keep playback synchronized for listeners (play, pause, next track and changes in playback position).
- Provide private and shared listening modes, with owner-controlled invitations or permissions.
- Ensure users have control over incoming audio and can disconnect or mute sources.

### Playback and audio improvements
- Playback modes: sequential, shuffle, repeat one, repeat all (Discman and Vinyl Player).
- Configurable audio buffer/cache, with investigation and fixes for brief interruptions when opening or closing inventories, chests and other screens.
- Persist appropriate playback preferences; optimize decoding and buffering based on profiling before exposing final settings.

### Inventory music controls
- Restore the vanilla crafting arrow and output slot to their normal positions.
- Move existing playback controls into an expandable tab on the right side of the inventory.
- Extend the panel with playback mode, repeat and personal volume controls.
- Add an incoming-audio mixer listing nearby players whose music is audible.
- Per-player independent volume and mute, stored against UUID rather than nickname; muting changes only the listener's own audio.
- Group multiple audio sources belonging to the same player where appropriate.

### Linkable headphones
- Headphones can be paired with an identifiable Discman or Vinyl Player and re-paired to another compatible device at any time.
- A player can share a session by giving another player headphones linked to their own Discman.
- Support synchronized listening with an independent volume for each listener.
- Store pairing across sessions and use stable device identity rather than server URL.
- Provide private/shared pairing modes, configurable reach and owner permission to disconnect listeners.

## v1.2.0 — Music Expansion (planned)
- Speakers, jukebox/rockola and additional music devices.
- Further creative display/decorative additions as separately scoped.

## Validation before release
- Test dedicated and integrated servers, multiple players and reconnections.
- Test shutdown, inventory/chest opening, audio buffer underruns, source changes and volume/mute behavior.
- Avoid distributing copyrighted audio content; consider bandwidth and server load.
